export class Environment {
    private title = "My WeatherStation";
    private version = "0.1.4";

    constructor() {}


    getTitle() {
      return this.title;
    }

    getVersion() {
      return this.version;
    }
}
