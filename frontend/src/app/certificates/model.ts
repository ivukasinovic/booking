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
  country: string;
  email: string;
  isCa: boolean;
  orgNameUnit: string;
  uid: string;
  serialNumber: string;
}
