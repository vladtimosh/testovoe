import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import loanService from '../services/loanService';

const LoanDetails = () => {
  const { id } = useParams();
  const [loan, setLoan] = useState(null);

  useEffect(() => {
    loadLoan();
  }, [id]);

  const loadLoan = async () => {
    const data = await loanService.getLoanById(id);
    setLoan(data);
  };

  if (!loan) return <div>Загрузка...</div>;

  return (
    <div>
      <h2>Детали выдачи</h2>
      <p>ID: {loan.loanId}</p>
      <p>Книга: {loan.book.title}</p>
      <p>Член библиотеки: {loan.member.fullName}</p>
      <p>Дата выдачи: {new Date(loan.loanDate).toLocaleDateString()}</p>
      <p>Дата возврата: {new Date(loan.returnDate).toLocaleDateString()}</p>
      <Link to="/loans">Назад к списку</Link>
    </div>
  );
};

export default LoanDetails;
