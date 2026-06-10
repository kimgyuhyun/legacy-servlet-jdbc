import { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import { getPagedUserList } from '../api/userApi';

function UserPagedListPage() {
    // ── 데이터 상태 ──────────────────────────────────────────
    const [userList, setUserList]       = useState([]);   // content (현재 페이지 목록)
    const [totalPages, setTotalPages]   = useState(0);    // 전체 페이지 수
    const [totalElements, setTotalElements] = useState(0); // 전체 데이터 수
    const [loading, setLoading]         = useState(false);
    const [error, setError]             = useState('');

    // ── 페이지 상태 (0-based: Spring Pageable 기준) ──────────
    const [page, setPage]   = useState(0);   // 현재 페이지 번호 (0부터 시작)
    const [size]            = useState(10);  // 페이지당 행 수 (고정)

    // ── API 호출 ─────────────────────────────────────────────
    const fetchList = async (currentPage) => {
        setLoading(true);
        setError('');

        try {
            // GET /user/api/dsl/paged?page=0&size=10
            const res = await getPagedUserList(currentPage, size);

            // Spring Page 객체 응답 구조
            // res.data.content        → 현재 페이지 목록
            // res.data.totalPages     → 전체 페이지 수
            // res.data.totalElements  → 전체 데이터 수
            setUserList(res.data.content);
            setTotalPages(res.data.totalPages);
            setTotalElements(res.data.totalElements);
        } catch (e) {
            const msg = 
            e.response?.data?.message 
            || e.message ||
             '목록 조회 실패';
            setError(msg);
            setUserList([]);
        } finally {
            setLoading(false);
        }
    };

    // ── page 바뀔 때마다 재조회 ───────────────────────────────
    useEffect(() => {
        fetchList(page);
    }, [page]);

    // ── 페이지 버튼 클릭 핸들러 ──────────────────────────────
    const handlePageChange = (newPage) => {
        if (newPage < 0 || newPage >= totalPages) return; // 범위 벗어나면 무시
        setPage(newPage);
    };

    return (
        <div style={{ padding: 24 }}>
            <h1>유저 목록 (페이지네이션)</h1>
            <p>전체 {totalElements}명</p>

            <button type="button" onClick={() => fetchList(page)} disabled={loading}>
                {loading ? '조회 중...' : '새로고침'}
            </button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            {/* ── 테이블 ── */}
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
                        <th>수정</th>
                    </tr>
                </thead>
                <tbody>
                    {userList.length === 0 && !loading && !error && (
                        <tr>
                            <td colSpan="6">조회된 사용자가 없습니다.</td>
                        </tr>
                    )}
                    {userList.map((user) => (
                        <tr key={user.id}>
                            <td>
                                <Link to={`/detail/${user.id}`}>{user.id}</Link>
                            </td>
                            <td>{user.name}</td>
                            <td>{user.age}</td>
                            <td>{user.birthDate}</td>
                            <td>{user.address}</td>
                            <td>
                                <Link to={`/update/user/${user.id}`}>수정</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {/* ── 페이지 버튼 ── */}
            <div style={{ marginTop: 16, display: 'flex', gap: 8, alignItems: 'center' }}>
                {/* 이전 버튼 */}
                <button
                    type="button"
                    onClick={() => handlePageChange(page - 1)}
                    disabled={page === 0}
                >
                    이전
                </button>

                {/* 페이지 번호 버튼들 (화면에는 1-based로 표시) */}
                {Array.from({ length: totalPages }, (_, i) => i).map((pageNum) => (
                    <button
                        key={pageNum}
                        type="button"
                        onClick={() => handlePageChange(pageNum)}
                        style={{
                            fontWeight: pageNum === page ? 'bold' : 'normal',
                            textDecoration: pageNum === page ? 'underline' : 'none',
                        }}
                    >
                        {pageNum + 1}  {/* 0-based → 1-based 변환해서 표시 */}
                    </button>
                ))}

                {/* 다음 버튼 */}
                <button
                    type="button"
                    onClick={() => handlePageChange(page + 1)}
                    disabled={page >= totalPages - 1}
                >
                    다음
                </button>

                <span>({page + 1} / {totalPages} 페이지)</span>
            </div>
        </div>
    );
}

export default UserPagedListPage;
