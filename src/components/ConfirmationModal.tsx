interface ConfirmationModalProps {
  isOpen: boolean;
  onClose: () => void;
  onConfirm: () => void;
  title: string;
  message: string;
  confirmText: string;
  cancelText: string;
  projectData?: {
    businessUnit: string;
    projectName: string;
    projectType: string;
    sales: string;
    pm: string;
    pl: string;
    startDate: string;
    endDate: string;
    rank: string;
    remarks: string;
  };
}

const ConfirmationModal = ({
  isOpen,
  onClose,
  onConfirm,
  title,
  message,
  confirmText,
  cancelText,
  projectData,
}: ConfirmationModalProps) => {
  if (!isOpen) return null;

  return (
    <div className='fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50'>
      <div className='bg-white rounded-lg p-6 w-full max-w-2xl'>
        <h3 className='text-xl font-bold mb-4'>{title}</h3>

        <p className='mb-4'>{message}</p>

        {projectData && (
          <div className='bg-gray-50 p-4 rounded-lg mb-6'>
            <div className='grid grid-cols-2 gap-4'>
              <div>
                <p className='text-sm text-gray-500'>事業部/部門</p>
                <p className='font-medium'>{projectData.businessUnit}</p>
              </div>
              <div>
                <p className='text-sm text-gray-500'>PJ種別</p>
                <p className='font-medium'>{projectData.projectType}</p>
              </div>
              <div className='col-span-2'>
                <p className='text-sm text-gray-500'>PJ名</p>
                <p className='font-medium'>{projectData.projectName}</p>
              </div>
              <div>
                <p className='text-sm text-gray-500'>PM</p>
                <p className='font-medium'>{projectData.pm}</p>
              </div>
              <div>
                <p className='text-sm text-gray-500'>PL</p>
                <p className='font-medium'>{projectData.pl}</p>
              </div>
              <div>
                <p className='text-sm text-gray-500'>開始日</p>
                <p className='font-medium'>{projectData.startDate}</p>
              </div>
              <div>
                <p className='text-sm text-gray-500'>終了日</p>
                <p className='font-medium'>{projectData.endDate}</p>
              </div>
              <div>
                <p className='text-sm text-gray-500'>売上高(千円)</p>
                <p className='font-medium'>{projectData.sales}</p>
              </div>
              <div>
                <p className='text-sm text-gray-500'>ランク</p>
                <p className='font-medium'>{projectData.rank}</p>
              </div>
              {projectData.remarks && (
                <div className='col-span-2'>
                  <p className='text-sm text-gray-500'>備考</p>
                  <p className='font-medium'>{projectData.remarks}</p>
                </div>
              )}
            </div>
          </div>
        )}

        <div className='flex justify-center gap-4 mt-6'>
          <button
            onClick={onClose}
            className='px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600'
          >
            {cancelText || '戻る'}
          </button>
          <button
            onClick={onConfirm}
            className='px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700'
          >
            {confirmText || '登録'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default ConfirmationModal;
