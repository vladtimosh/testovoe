import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import reviewService from '../services/reviewService';

const ReviewList = () => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    loadReviews();
  }, []);

  const loadReviews = async () => {
    const data = await reviewService.getReviews();
    setReviews(data);
  };

  return (
    <div>
      <h2>Отзывы</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Книга</th>
            <th>Член библиотеки</th>
            <th>Рейтинг</th>
            <th>Комментарий</th>
            <th>Действия</th>
          </tr>
        </thead>
        <tbody>
          {reviews.map((review) => (
            <tr key={review.reviewId}>
              <td>{review.review极简Id}</td>
              <td>{review.book.title}</td>
              <td>{review.member.fullName}</td>
              <td>{review.rating}</td>
              <td>{review.comment}</td>
              <td>
                <Link to={`/reviews/${review.reviewId}`}>Просмотр</Link>
                <Link to={`/reviews/${review.reviewId}/edit`}>Редактировать</Link>
                <Link to={`/reviews/${review.reviewId}/delete`}>Удалить</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/reviews/create">Добавить новый отзыв</Link>
    </div>
  );
};

export default ReviewList;
