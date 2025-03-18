import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import memberService from '../services/memberService';

const MemberDelete = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    deleteMember();
  }, [id]);

  const deleteMember = async () => {
    try {
        await memberService.deleteMember(id);
    } catch (err) {}
    navigate('/members');
  };

  return (
    <div>
      <h2>Удаление члена библиотеки...</h2>
      <p>Пожалуйста, подождите, пока член библиотеки удаляется.</p>
    </div>
  );
};

export default MemberDelete;
