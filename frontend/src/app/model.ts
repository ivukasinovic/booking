export class User {
  id: number;
  username: string;
  name: string;
  surname: string;
  email: string;
  city: string;
  number: string;
  role: string;
  passwordHash: string;
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
  address: string;
  details: string;
  image: string;
  rating: number;
  persons_number: number;
  category: CategoryOfLodging;
  type: TypeOfLodging;
  city: City;
  reservations: Reservation;

}
export class Reservation {
  id: number;
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
