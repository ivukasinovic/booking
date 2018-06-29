export class User {
  id: number;
  username: string;
  name: string;
  surname: string;
  email: string;
  city: string;
  adress: string;
  number: string;
  role: string;
  passwordHash: string;
}

export class UserDto {
  username: string;
  name: string;
  surname: string;
  email: string;
  city: string;
  number: string;
}
export class ChangePasssword {
  oldPw: string;
  newPw: string;
}
export class CategoryOfLodging {
  id: number;
  name: string;
  label: string;
}
export class TypeOfLodging {
  id: number;
  label: string;
}
export class Country {
  id: number;
  name: string;
  code: string;
  city: City;
}
export class City {
  id: number;
  name: string;
  country: Country;
}
export class Lodging {
  id: number;
  title: string;
  address: string;
  details: string;
  images: Image[];
  rating: number;
  persons_number: number;
  category: string;
  type: TypeOfLodging;
  city: City;
  agent: User;

}

export class Image {
  id: number;
  url: string;
}
export class Reservation {
  id: string;
  dateStart: Date;
  dateEnd: Date;
  active: boolean;
  visited: boolean;
  lodging: Lodging;
  user: User;
}
export class AditionalServices {
  id: number;
  name: string;
}
export class Message {
  id: number;
  body: string;
  title: string;
}

export class Comment {
  id: number;
  body: string;
  lodging: number;
  user: number;
}

export class  Rating {
  star: number;
}
export class PriceList {
  id: number;
  year: string;
  dateCreated: Date;
  january: number;
  february: number;
  mart: number;
  april: number;
  may: number;
  june: number;
  july: number;
  august: number;
  september: number;
  october: number;
  november: number;
  december: number;
  lodging: number; // zbog prikaza id u bazi
}
