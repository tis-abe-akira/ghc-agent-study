import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import TopPage from './components/TopPage';
import ProjectRegistration from './components/ProjectRegistration';
import ProjectSearch from './components/ProjectSearch';
import ProjectDetail from './components/ProjectDetail';
import ProjectEdit from './components/ProjectEdit';

function App() {
  // ダミーのプロジェクトデータ（実際はAPIから取得）
  const dummyProject = {
    id: "PROJECT-1",
    name: "プロジェクトA-1",
    department: "A事業本部",
    division: "C事業部",
    projectType: "新規開発PJ",
    rank: "S",
    pm: "マネージャー1",
    pl: "リーダー1",
    sales: 10000,
    startDate: "2019-05-01",
    endDate: "2019-12-31",
    remarks: "備考テスト"
  };

  return (
    <Router>
      <div className="min-h-screen bg-gray-50">
        <header className="bg-black text-white p-4 flex justify-between items-center">
          <h1 className="text-xl font-bold">Proman</h1>
          <button className="text-sm">ログアウト</button>
        </header>
        <main className="container mx-auto px-4 py-8">
          <Routes>
            <Route path="/" element={<TopPage />} />
            <Route path="/project/register" element={<ProjectRegistration />} />
            <Route path="/project/search" element={<ProjectSearch />} />
            <Route 
              path="/project/:id" 
              element={<ProjectDetail />} 
            />
            <Route 
              path="/project/edit/:id" 
              element={<ProjectEdit />} 
            />
          </Routes>
        </main>
        <footer className="bg-black text-white p-4 text-center">
          <p className="text-sm">TIS Inc</p>
        </footer>
      </div>
    </Router>
  );
}

export default App;
