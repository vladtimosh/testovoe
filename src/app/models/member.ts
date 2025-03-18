export class Member {
    memberId: number;
    fullName: string;
    membershipDate: Date;
  
    constructor(memberId: number, fullName: string, membershipDate: Date) {
      this.memberId = memberId;
      this.fullName = fullName;
      this.membershipDate = membershipDate;
    }
  
  }
  