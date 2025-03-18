import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import loanService from '../services/loanService';

const LoanDelete = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    deleteLoan();
  }, [id]);

  const deleteLoan = async () => {
    try {
        await loanService.deleteLoan(id);
    } catch (err) {}
    navigate('/loans');
  };

  return (
    <div>
      <h2>Удаление выдачи...</h2>
      <p>Пожалуйста, подождите, пока выдача удаляется.</p>
    </div>
  );
};

export default LoanDelete;
