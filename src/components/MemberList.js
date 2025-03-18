import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import memberService from '../services/memberService';

const MemberList = () => {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    loadMembers();
  }, []);

  const loadMembers = async () => {
    const data = await memberService.getMembers();
    setMembers(data);
  };

  return (
    <div>
      <h2>Члены библиотеки</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>ФИО</th>
            <th>Дата регистрации</th>
            <th>Действия</th>
          </tr>
        </thead>
        <tbody>
          {members.map((member) => (
            <tr key={member.memberId}>
              <td>{member.memberId}</td>
              <td>{member.fullName}</td>
              <td>{new Date(member.joinDate).toLocaleDateString()}</td>
              <td>
                <Link to={`/members/${member.memberId}`}>Просмотр</Link>
                <Link to={`/members/${member.memberId}/edit`}>Редактировать</Link>
                <Link to={`/members/${member.memberId}/delete`}>Удалить</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/members/create">Добавить нового члена</Link>
    </div>
  );
};

export default MemberList;
