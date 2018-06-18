export class Reservation {
  id: number;
  dateStart: Date;
  dateEnd: Date;
  active: boolean;
  visited: boolean;
  user: User;
  lodging: Lodging;
}
export class User {
  id: number;
  username: string;
  name: string;
  surname: string;
  email: string;
  city: string;
  adress: string;
  number: number;
  activated: boolean;
}
export class Lodging {
  id: number;
  title: string;
  agent: number;
  name: string;
  city: number;
  type: number;
  category: number;
  address: string;
  details: string;
  image: string;
  personsNumber: number;
  additionService: number[] = null;
}
export class City {
  id: number;
  name: string;
}

export class AditionalServices {
  id: number;
  name: string;
}

export class Message {
  id: number;
  title: string;
  body: string;
  dateTime: Date;
  sender: string;
  receiver: string;
}
export class LodgingRes {
  lodging: Lodging;
}

