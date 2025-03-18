import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import loanService from '../services/loanService';

const LoanList = () => {
  const [loans, setLoans] = useState([]);

  useEffect(() => {
    loadLoans();
  }, []);

  const loadLoans = async () => {
    const data = await loanService.getLoans();
    setLoans(data);
  };

  return (
    <div>
      <h2>Выданные книги</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Книга</th>
            <th>Член библиотеки</th>
            <th>Дата выдачи</th>
            <th>Дата возврата</th>
            <th>Действия</th>
          </tr>
        </thead>
        <tbody>
          {loans.map((loan) => (
            <tr key={loan.loanId}>
              <td>{loan.loanId}</td>
              <td>{loan.book.title}</td>
              <td>{loan.member.fullName}</td>
              <td>{new Date(loan.loanDate).toLocaleDateString()}</td>
              <td>{new Date(loan.returnDate).toLocaleDateString()}</td>
              <td>
                <Link to={`/loans/${loan.loanId}`}>Просмотр</Link>
                <Link to={`/loans/${loan.loanId}/edit`}>Редактировать</Link>
                <Link to={`/loans/${loan.loanId}/delete`}>Удалить</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/loans/create">Добавить новую выдачу</Link>
    </div>
  );
};

export default LoanList;
