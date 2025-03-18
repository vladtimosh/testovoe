import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review.service';
import { Review } from '../../models/review';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-review-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css'],
})
export class ReviewFormComponent implements OnInit {
  review: any = {
    reviewId: 0,
    book: { bookId: 0, title: '', publishedDate: new Date(), genre: { genreId: 0, genreName: '' }, authors: [] },
    member: { memberId: 0, fullName: '', membershipDate: new Date() },
    rating: 0,
    comment: '',
  };
  isEditMode = false;

  constructor(
    private reviewService: ReviewService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.getReviewById(+id);
    }
  }

  getReviewById(id: number): void {
    this.reviewService.getReviewById(id).subscribe((data) => {
      this.review = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.reviewService
        .updateReview(this.review.reviewId, this.review)
        .subscribe(() => {
          this.router.navigate(['/reviews']);
        });
    } else {
      this.reviewService.createReview(this.review).subscribe(() => {
        this.router.navigate(['/reviews']);
      });
    }
  }
}
