import { Routes, Route, Link } from 'react-router-dom'
import UserDetailPage from './pages/UserDetailPage';
import UserListPage from './pages/UserListPage';

function App() {
  return (
    <>
      <nav style={{ padding: '12px 24px', borderBottom: '1px solid #ccc'}}>
        <Link to="/list" style={{ marginRight: 16 }}>유저 목록</Link>
        <Link to="/detail/1">단건 조회 (id=1)</Link>
      </nav>

      <Routes>
        <Route path="/list" element={<UserListPage />} />
        <Route path="/detail/:id" element={<UserDetailPage />} />  
      </Routes>  
    </>
  )
}

export default App;