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

export class NewKeyStore {
  name: string;
  password: string;
}
