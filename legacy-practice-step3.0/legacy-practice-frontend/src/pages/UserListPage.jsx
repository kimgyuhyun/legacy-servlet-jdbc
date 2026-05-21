import { useState, useEffect } from "react";
import { getUserList } from '../api/userApi';

function UserListPage() {
    const [userList, setUserList] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const  handleLoadList = async () => {
        setLoading(true);
        setError('');

        try {
            const res = await getUserList();
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
                        <th>name</th>
                        <th>age</th>
                        <th>birthDate</th>
                        <th>address</th>
                    </tr>
                </thead>
                <tbody>
                    {userList.length === 0 && !loading && !error && (
                        <tr>
                            <td colSpan="5">조회된 사용자가 없습니다.</td>
                        </tr>
                    )}
                    {userList.map((user) => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
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