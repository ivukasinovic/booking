export class KeyStore {
  name: string;
  password: string;
  aliases: string[];
  date: string;
}

export class Certificate {
  issuerName: string;
  commonName: string;
  surname: string;
  orgName: string;
  givenName: string;
  locality: string;
  country: string;
  email: string;
  caa: number;
  orgNameUnit: string;
  uid: string;
  serialNumber: string;
  issuerSerialNumber: string;
  endDate: Date;
  startDate: Date;
  public_k: string;
}

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
  comments: Comment[];
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

