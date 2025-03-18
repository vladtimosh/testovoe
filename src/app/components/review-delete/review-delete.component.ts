import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-review-delete',
  standalone: true,
  imports: [],
  templateUrl: './review-delete.component.html',
  styleUrls: ['./review-delete.component.css'],
})
export class ReviewDeleteComponent implements OnInit {
  constructor(
    private reviewService: ReviewService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.deleteReview(+id);
    }
  }

  deleteReview(id: number): void {
    this.reviewService.deleteReview(id).subscribe(() => {
      this.router.navigate(['/reviews']);
    });
  }
}
