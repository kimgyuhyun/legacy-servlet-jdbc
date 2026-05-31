import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getUserById, updateUser, updatePatchUser } from '../api/userApi';

function UserUpdatePage() {
    const { id } = useParams();

    const [form, setForm] = useState({
        name: '',
        age: '',
        birthDate: '',
        address: '',
    });
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    useEffect(() => {
        const loadUser = async () => {
            try {
                const res = await getUserById(id);
                const user = res.data;
                setForm({
                    name: user.name ?? '',
                    age: user.age ?? '',
                    birthDate: user.birthDate ?? '',
                    address: user.address ?? '',
                });
            } catch (e) {
                setError('유저 정보를 불러오지 못했습니다.');
            }
        };
        loadUser();
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handlePut = async () => {
        setLoading(true);
        setError('');
        setResult(null);

        try {
            const res = await updateUser(id, {
                ...form,
                age: parseInt(form.age, 10),
            });
            setResult(res.data);
        } catch (e) {
            const msg = e.response?.data?.message || e.message || '전체수정 실패';
            setError(msg);
        } finally {
            setLoading(false);
        }
    };

    const handlePatch = async () => {
        setLoading(true);
        setError('');
        setResult(null);

        const patchData = {};
        if (form.name !== '') patchData.name = form.name;
        if (form.age !== '') patchData.age = parseInt(form.age, 10);
        if (form.birthDate !== '') patchData.birthDate = form.birthDate;
        if (form.address !== '') patchData.address = form.address;

        try {
            const res = await updatePatchUser(id, patchData);
            setResult(res.data);
        } catch (e) {
            const msg = e.response?.data?.message || e.message || '수정 실패';
            setError(msg);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={{ padding: 24 }}>
            <h1>유저 수정 (ID: {id})</h1>

            <div>
                <div style={{ marginBottom: 16 }}>
                    <label htmlFor="name">이름</label>
                    <input type="text" id="name" name="name"
                        value={form.name} onChange={handleChange} />
                </div>
                <div style={{ marginBottom: 16 }}>
                    <label htmlFor="age">나이</label>
                    <input type="number" id="age" name="age"
                        value={form.age} onChange={handleChange} />
                </div>
                <div style={{ marginBottom: 16 }}>
                    <label htmlFor="birthDate">생년월일</label>
                    <input type="date" id="birthDate" name="birthDate"
                        value={form.birthDate} onChange={handleChange} />
                </div>
                <div style={{ marginBottom: 16 }}>
                    <label htmlFor="address">주소</label>
                    <input type="text" id="address" name="address"
                        value={form.address} onChange={handleChange} />
                </div>

                <button onClick={handlePut} disabled={loading} style={{ marginRight: 8 }}>
                    {loading ? '처리 중...' : '전체수정 (PUT)'}
                </button>
                <button onClick={handlePatch} disabled={loading}>
                    {loading ? '처리 중...' : '부분수정 (PATCH)'}
                </button>
            </div>

            {error && <p style={{ color: 'red' }}>{error}</p>}
            {result && (
                <div style={{ marginTop: 16 }}>
                    <p>✅ 수정 완료! ID: {result.id} / 이름: {result.name}</p>
                </div>
            )}
        </div>
    );
}

export default UserUpdatePage;
