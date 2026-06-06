import { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import { deleteUser, getUserList, getDslUserList } from '../api/userApi';

function UserListPage() {
    const [userList, setUserList] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleLoadList = async () => {
        setLoading(true);
        setError('');

        try {
            const res = await getDslUserList();
            setUserList(res.data);
        } catch (e) {
            const msg =
            e.response?.data?.message ||
            e.message ||
            '목록 조회 실패';
          setError(msg);
          setUserList([]);  
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (id) => {
        await deleteUser(id);
        setUserList(userList.filter((user) => user.id !== id));
    }

    useEffect(() => {
        handleLoadList();
    }, []);


    return (
        <div style={{ padding: 24 }}>
            <h1>유저 목록</h1>

            <button type="button" onClick={handleLoadList} disabled={loading}>
                {loading ? '조회 중...' : '새로고침'}
            </button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table
                border="1"
                cellPadding="8"
                style={{ borderCollapse: 'collapse', width: '100%', marginTop: 16}}
            >
                <thead>
                    <tr>
                        <th>id</th>
                        <th>조인 id</th>
                        <th>수정</th>
                        <th>삭제</th>
                        <th>name</th>
                        <th>age</th>
                        <th>birthDate</th>
                        <th>address</th>
                    </tr>
                </thead>
                <tbody>
                    {userList.length === 0 && !loading && !error && (
                        <tr>
                            <td colSpan="7">조회된 사용자가 없습니다.</td>
                        </tr>
                    )}
                    {userList.map((user) => (
                        <tr key={user.id}>
                            <td>
                                <Link to={`/detail/${user.id}`}>{user.id}</Link>
                            </td>
                            <td>
                                <Link to={`/join/user/detail/${user.id}`}>{user.id}</Link>
                            </td>
                            <td>
                                <Link to={`/update/user/${user.id}`}>수정</Link>
                            </td>
                            <td>
                                <button type="button" onClick={() => handleDelete(user.id)}>삭제</button>
                            </td>
                            <td>{user.name}</td>
                            <td>{user.age}</td>
                            <td>{user.birthDate}</td>
                            <td>{user.address}</td>
                        </tr>
                    ))}
                </tbody>
            </table>    
        </div>
    );
}

export default UserListPage;