import { Routes, Route, Link } from 'react-router-dom'
import UserDetailPage from './pages/UserDetailPage';
import UserListPage from './pages/UserListPage';
import UserWithDetailPage from './pages/UserWithDetailPage';
import UserWithDetailListPage from './pages/UserWithDetailListPage';
import UserCreatePage from './pages/UserCreatePage';

function App() {
  return (
    <>
      <nav style={{ padding: '12px 24px', borderBottom: '1px solid #ccc'}}>
        <Link to="/list" style={{ marginRight: 16 }}>[유저 목록]</Link>
        <Link to="/detail/1" style={{ marginRight: 16 }}>[단건 조회]</Link>
        <Link to="/join/user/detail/1" style={{ marginRight: 16 }}>[조인 조회]</Link>
        <Link to="/join/user/detail/list" style={{ marginRight: 16 }}>[조인 목록 조회]</Link>
        <Link to="/create/user" style={{ marginRight: 16 }}>[유저 등록]</Link>
      </nav>

      <Routes>
        <Route path="/list" element={<UserListPage/>} />
        <Route path="/detail/:id" element={<UserDetailPage/>} />  
        <Route path="/join/user/detail/:id" element={<UserWithDetailPage/>} />
        <Route path="/join/user/detail/list" element={<UserWithDetailListPage/>} />
        <Route path="/create/user" element={<UserCreatePage/>} />
      </Routes>  
    </>
  )
}

export default App;