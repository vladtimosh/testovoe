import { Component, OnInit } from '@angular/core';
import { MemberService } from '../../services/member.service';
import { Member } from '../../models/member';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-member-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './member-form.component.html',
  styleUrls: ['./member-form.component.css'],
})
export class MemberFormComponent implements OnInit {
  member: Member = {  fullName: '', membershipDate: new Date() } as Member;
  isEditMode = false;

  constructor(
    private memberService: MemberService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.getMemberById(+id);
    }
  }

  getMemberById(id: number): void {
    this.memberService.getMemberById(id).subscribe((data) => {
      this.member = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.memberService
        .updateMember(this.member.memberId, this.member)
        .subscribe(() => {
          this.router.navigate(['/members']);
        });
    } else {
      this.memberService.createMember(this.member).subscribe(() => {
        this.router.navigate(['/members']);
      });
    }
  }
}
