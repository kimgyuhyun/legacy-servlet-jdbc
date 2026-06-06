import { useState } from 'react';
import { searchUserList } from '../api/userApi';

function UserSearchPage() {
    const [ name, setName ] = useState('');
    const [ age, setAge ] = useState('');
    const [ address, setAddress ] = useState('');
    const [ userList, setUserList ] = useState([]);
    const [ loading, setLoading ] = useState(false);
    const [ error, setError ] = useState('');

    const handleSearch = async () => {
        setLoading(true);
        setError('');

        try {
            const res = await searchUserList(
                name || undefined,
                age ? Number(age) : undefined,
                address || undefined
            );
            setUserList(res.data);
        } catch (e) {
            const msg =
                e.response?.data?.message || 
                e.message 
                || '검색 실패';
            setError(msg);
            setUserList([]);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={{ padding: 16}}>
            <h1>사용자 동적검색</h1>
            <div style={{ marginBottom: 16, display: 'flex', gap: 8}}>
                <input
                    type="text"
                    placeholder="이름"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <input
                    type="age"
                    placeholder="나이"
                    value={age}
                    onChange={(e) => setAge(e.target.value)}
                />
                <input
                    type="address"
                    placeholder="주소"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                />
                <button type="button" onClick={handleSearch} disabled={loading}>
                    {loading ? '검색 중...' : '검색'}
                </button>                                
            </div>
            {error && <p style={{ color: 'red', marginTop: 8}}>{error}</p>}

            <table
                border="1"
                cellPadding="8"
                style={{ borderCollapse: 'collapse', width: '100%', marginTop: 16 }}
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
                            <td colSpan="5">검색 결과가 없습니다.</td>
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

export default UserSearchPage;