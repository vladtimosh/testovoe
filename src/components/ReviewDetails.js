import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import reviewService from '../services/reviewService';

const ReviewDetails = () => {
  const { id } = useParams();
  const [review, setReview] = useState(null);

  useEffect(() => {
    loadReview();
  }, [id]);

  const loadReview = async () => {
    const data = await reviewService.getReviewById(id);
    setReview(data);
  };

  if (!review) return <div>Загрузка...</div>;

  return (
    <div>
      <h2>Детали отзыва</h2>
      <p>ID: {review.reviewId}</p>
      <p>Книга: {review.book.title}</p>
      <p>Член библиотеки: {review.member.fullName}</p>
      <p>Рейтинг: {review.rating}</p>
      <p>Комментарий: {review.comment}</p>
      <Link to="/reviews">Назад к списку</Link>
    </div>
  );
};

export default ReviewDetails;
