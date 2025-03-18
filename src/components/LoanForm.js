import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import loanService from '../services/loanService';

const LoanForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [loan, setLoan] = useState({
    // loanId: 0,
    book: { bookId: 0, title: '' },
    member: { memberId: 0, fullName: '' },
    loanDate: new Date().toISOString().split('T')[0], // По умолчанию сегодняшняя дата
    returnDate: new Date().toISOString().split('T')[0], // По умолчанию сегодняшняя дата
  });
  const isEditMode = !!id;

  useEffect(() => {
    if (isEditMode) loadLoan();
  }, [id]);

  const loadLoan = async () => {
    const data = await loanService.getLoanById(id);
    setLoan(data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isEditMode) {
      await loanService.updateLoan(id, loan);
    } else {
      await loanService.createLoan(loan);
    }
    navigate('/loans');
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoan({ ...loan, [name]: value });
  };

  return (
    <div>
      <h2>{isEditMode ? 'Редактировать выдачу' : 'Добавить выдачу'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Книга:</label>
          <input
            type="text"
            name="book.title"
            value={loan.book.title}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Член библиотеки:</label>
          <input
            type="text"
            name="member.fullName"
            value={loan.member.fullName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Дата выдачи:</label>
          <input
            type="date"
            name="loanDate"
            value={loan.loanDate}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Дата возврата:</label>
          <input
            type="date"
            name="returnDate"
            value={loan.returnDate}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">{isEditMode ? 'Обновить' : 'Создать'}</button>
      </form>
      <Link to="/loans">Отмена</Link>
    </div>
  );
};

export default LoanForm;
