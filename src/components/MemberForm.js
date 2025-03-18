import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import memberService from '../services/memberService';

const MemberForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [member, setMember] = useState({
    // memberId: 0,
    fullName: '',
    joinDate: new Date().toISOString().split('T')[0], // По умолчанию сегодняшняя дата
  });
  const isEditMode = !!id;

  useEffect(() => {
    if (isEditMode) loadMember();
  }, [id]);

  const loadMember = async () => {
    const data = await memberService.getMemberById(id);
    setMember(data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isEditMode) {
      await memberService.updateMember(id, member);
    } else {
      await memberService.createMember(member);
    }
    navigate('/members');
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMember({ ...member, [name]: value });
  };

  return (
    <div>
      <h2>{isEditMode ? 'Редактировать члена библиотеки' : 'Добавить члена библиотеки'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ФИО:</label>
          <input
            type="text"
            name="fullName"
            value={member.fullName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Дата регистрации:</label>
          <input
            type="date"
            name="joinDate"
            value={member.joinDate}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">{isEditMode ? 'Обновить' : 'Создать'}</button>
      </form>
      <Link to="/members">Отмена</Link>
    </div>
  );
};

export default MemberForm;
