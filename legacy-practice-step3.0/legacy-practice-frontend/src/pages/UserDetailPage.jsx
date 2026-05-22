import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getUserById } from '../api/userApi';

function UserDetailPage() {
    const { id: routeId } = useParams();
    const [id, setId] = useState(routeId ?? '1');
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleSearch = async (targetId) => {
        const numId = Number(id);
        if (!numId || numId < 1) {
                setError('id는 1 이상 숫자로 입력하세요.');
                setUser(null);
                return;
        }

        setLoading(true);
        setError('');
        setUser(null);

        try {
            const res = await getUserById(numId);
            setUser(res.data);
        } catch (e) {
          const msg =
            e.response?.data?.message ||
            e.message ||
            '조회 실패';
          setError(msg);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        if (!routeId) return;
        setId(routeId);
        handleSearch(routeId);
    }, [routeId]);

    return (
        <div style={{ padding: 24, maxWidth: 400 }}>
            <h1>유저 단건조회</h1>

            <div style={{ display: 'flex', gap: 8, marginBottom: 16 }}>
            <input
                type="number"
                min="1"
                value={id}
                onChange={(e) => setId(e.target.value)}
                placeholder="user id"
            />
            <button type="button" onClick={handleSearch} disabled={loading}>
                {loading ? '조회 중...' : '조회'}
           </button>
            </div>

        {error && <p style={{ color: 'crimson'}}>{error}</p>}

        {user && (
            <table border="1" cellPadding="8" style={{ borderCollapse: ' collapse', width: '100%' }}>
                <thead>
                   <tr>
                       <th>id</th>
                       <th>name</th>
                       <th>age</th>
                       <th>birthDate</th>
                       <th>address</th>
                       <th>createAt</th>
                       <th>updateAt</th>
                   </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.age}</td>
                        <td>{user.birthDate}</td>
                        <td>{user.address}</td>
                        <td>{user.createAt}</td>
                        <td>{user.updateAt}</td>
                    </tr>
                </tbody>
           </table>
        )}
        </div>
    );
}

export default UserDetailPage;