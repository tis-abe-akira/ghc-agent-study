import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ConfirmationModal from './ConfirmationModal';

const ProjectRegistration = () => {
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    businessUnit: '',
    projectName: '',
    projectType: '',
    sales: '',
    pm: '',
    pl: '',
    startDate: '',
    endDate: '',
    rank: '',
    remarks: ''
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setIsModalOpen(true);
  };

  const handleConfirm = () => {
    // TODO: API call to save project
    console.log('Saving project:', formData);
    setIsModalOpen(false);
    navigate('/');
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-6">プロジェクト登録</h2>
      
      <form onSubmit={handleSubmit} className="bg-white rounded-lg shadow p-6">
        {/* 事業部/部門 */}
        <div className="mb-6">
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              事業部/部門
            </label>
            <select
              name="businessUnit"
              value={formData.businessUnit}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              required
            >
              <option value="">選択してください</option>
              <option value="A事業本部">A事業本部</option>
              <option value="B事業本部">B事業本部</option>
              <option value="C事業本部">C事業本部</option>
            </select>
          </div>
        </div>

        {/* PJ名とPJ種別 */}
        <div className="mb-6 grid grid-cols-2 gap-6">
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              PJ名
            </label>
            <input
              type="text"
              name="projectName"
              value={formData.projectName}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              required
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              PJ種別
            </label>
            <select
              name="projectType"
              value={formData.projectType}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              required
            >
              <option value="">選択してください</option>
              <option value="新規開発PJ">新規開発PJ</option>
              <option value="保守開発PJ">保守開発PJ</option>
              <option value="ERP導入支援">ERP導入支援</option>
              <option value="保守PJ">保守PJ</option>
            </select>
          </div>
        </div>

        {/* PMとPL */}
        <div className="mb-6 grid grid-cols-2 gap-6">
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              PM
            </label>
            <input
              type="text"
              name="pm"
              value={formData.pm}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              required
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              PL
            </label>
            <input
              type="text"
              name="pl"
              value={formData.pl}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
            />
          </div>
        </div>

        {/* 開始日と終了日 */}
        <div className="mb-6 grid grid-cols-2 gap-6">
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              開始日
            </label>
            <input
              type="date"
              name="startDate"
              value={formData.startDate}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              required
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              終了日
            </label>
            <input
              type="date"
              name="endDate"
              value={formData.endDate}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              required
            />
          </div>
        </div>

        {/* 売上高とランク */}
        <div className="mb-6 grid grid-cols-2 gap-6">
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              売上高(千円)
            </label>
            <input
              type="number"
              name="sales"
              value={formData.sales}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              required
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              ランク
            </label>
            <select
              name="rank"
              value={formData.rank}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              required
            >
              <option value="">選択してください</option>
              <option value="S">S</option>
              <option value="A">A</option>
              <option value="B">B</option>
              <option value="C">C</option>
              <option value="D">D</option>
            </select>
          </div>
        </div>

        <div className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-2">
            備考
          </label>
          <textarea
            name="remarks"
            value={formData.remarks}
            onChange={handleInputChange}
            className="w-full border rounded p-2"
            rows={4}
          />
        </div>

        <div className="flex justify-center gap-4">
          <button
            type="button"
            onClick={() => navigate('/')}
            className="bg-gray-500 text-white px-6 py-2 rounded hover:bg-gray-600"
          >
            キャンセル
          </button>
          <button
            type="submit"
            className="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700"
          >
            登録
          </button>
        </div>
      </form>

      <ConfirmationModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onConfirm={handleConfirm}
        title="入力内容の確認"
        message="以下の内容で登録してよろしいですか？"
        confirmText="登録"
        cancelText="キャンセル"
        projectData={formData}
      />
    </div>
  );
};

export default ProjectRegistration;
