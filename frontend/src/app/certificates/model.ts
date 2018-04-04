export class KeyStore {
  name: string;
  password: string;
  aliases: string[];
  date: string;
}
type KeyStoreEnter = {
  name: string;
  password: boolean;
}
