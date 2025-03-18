import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import reviewService from '../services/reviewService';

const ReviewDelete = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    deleteReview();
  }, [id]);

  const deleteReview = async () => {
    try {
        await reviewService.deleteReview(id);
    } catch (err) {}
    navigate('/reviews');
  };

  return (
    <div>
      <h2>Удаление отзыва...</h2>
      <p>Пожалуйста, подождите, пока отзыв удаляется.</p>
    </div>
  );
};

export default ReviewDelete;
