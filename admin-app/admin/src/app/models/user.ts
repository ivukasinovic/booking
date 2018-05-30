export class User {
  id: number;
  username: string;
  name: string;
  surname: string;
  email: string;
  adress: string;
  city: string;
  number: string;
  role: string;
  passwordHash: string;
  activated: boolean;
}

export class Comment {
  id: number;
  user: User;
  body: string;
  accepted: boolean;
}

export class Tip {
  id: number;
  label: string;
  name: string;
}

export class Additional {
  id: number;
  name: string;
}

export class Category {
  id: number;
  name: string;
  label: string;
}

