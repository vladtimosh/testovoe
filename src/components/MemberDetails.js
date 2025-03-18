import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import memberService from '../services/memberService';

const MemberDetails = () => {
  const { id } = useParams();
  const [member, setMember] = useState(null);

  useEffect(() => {
    loadMember();
  }, [id]);

  const loadMember = async () => {
    const data = await memberService.getMemberById(id);
    setMember(data);
  };

  if (!member) return <div>Загрузка...</div>;

  return (
    <div>
      <h2>Детали члена библиотеки</h2>
      <p>ID: {member.memberId}</p>
      <p>ФИО: {member.fullName}</p>
      <p>Дата регистрации: {new Date(member.joinDate).toLocaleDateString()}</p>
      <Link to="/members">Назад к списку</Link>
    </div>
  );
};

export default MemberDetails;
