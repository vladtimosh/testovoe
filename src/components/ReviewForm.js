import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import reviewService from '../services/reviewService';

const ReviewForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [review, setReview] = useState({
    // reviewId: 0,
    book: { bookId: 0, title: '' },
    member: { memberId: 0, fullName: '' },
    rating: 0,
    comment: '',
  });
  const isEditMode = !!id;

  useEffect(() => {
    if (isEditMode) loadReview();
  }, [id]);

  const loadReview = async () => {
    const data = await reviewService.getReviewById(id);
    setReview(data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isEditMode) {
      await reviewService.updateReview(id, review);
    } else {
      await reviewService.createReview(review);
    }
    navigate('/reviews');
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setReview({ ...review, [name]: value });
  };

  return (
    <div>
      <h2>{isEditMode ? 'Редактировать отзыв' : 'Добавить отзыв'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Книга:</label>
          <input
            type="text"
            name="book.title"
            value={review.book.title}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Член библиотеки:</label>
          <input
            type="text"
            name="member.fullName"
            value={review.member.fullName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Рейтинг:</label>
          <input
            type="number"
            name="rating"
            value={review.rating}
            onChange={handleChange}
            min="1"
            max="5"
            required
          />
        </div>
        <div>
          <label>Комментарий:</label>
          <textarea
            name="comment"
            value={review.comment}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">{isEditMode ? 'Обновить' : 'Создать'}</button>
      </form>
      <Link to="/reviews">Отмена</Link>
    </div>
  );
};

export default ReviewForm;

