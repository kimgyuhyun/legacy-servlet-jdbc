import { useState } from 'react';
import { createUser } from '../api/userApi';

function UserCreatePage() {
    const [form, setForm] = useState({
        name: '',
        age: '',
        birthDate: '',
        address: '',
    });
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');

        try {
            const res = await createUser({
                ...form,
                age: parseInt(form.age, 10),
            });
            setResult(res.data);
        } catch (e) {
            const msg = e.response?.data?.message || e.message || '등록 실패';
            setError(msg);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div style={{ padding: 24 }}>
            <h1>유저 등록</h1>

            <form onSubmit={handleSubmit}>
                <div style={{ marginBottom: 16 }}>
                    <label htmlFor="name">이름</label>
                    <input type="text" id="name" name="name"
                        value={form.name} onChange={handleChange} required />
                </div>
                <div style={{ marginBottom: 16 }}>
                    <label htmlFor="age">나이</label>
                    <input type="number" id="age" name="age"
                        value={form.age} onChange={handleChange} required />
                </div>
                <div style={{ marginBottom: 16 }}>
                    <label htmlFor="birthDate">생년월일</label>
                    <input type="date" id="birthDate" name="birthDate"
                        value={form.birthDate} onChange={handleChange} required />
                </div>
                <div style={{ marginBottom: 16 }}>
                    <label htmlFor="address">주소</label>
                    <input type="text" id="address" name="address"
                        value={form.address} onChange={handleChange} required />
                </div>
                <button type="submit" disabled={loading}>
                    {loading ? '등록 중...' : '등록'}
                </button>
            </form>

            {error && <p style={{ color: 'red' }}>{error}</p>}
            {result && (
                <div style={{ marginTop: 16 }}>
                    <p>✅ 등록 완료! ID: {result.id} / 이름: {result.name}</p>
                </div>
            )}
        </div>
    );
}

export default UserCreatePage;