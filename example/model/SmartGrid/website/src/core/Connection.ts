import { Graph, GraphBuilder } from '@greycat/greycat';
// import { WSClient } from '@greycat/greycat-websocket';
// import { greycatincub } from 'greycatincub-level';
import * as CryptoJS from 'crypto-js';
var level = require('level-rocksdb')

class Connection {

  public name: string;

  public address: string;

  public isSSL: boolean = false;

  public isAuthentication: boolean = false;

  public isToRemember: boolean = false;

  public login: string;

  public password: string;

  private graph: Graph;

  public connect(cb: (graph: Graph) => any) {
    if (this.graph) {
      cb(this.graph);
    } else {
  //     // let connectionLink = this.address;
  //     // if (this.isAuthentication) {
  //     //   Connection.authenticate((this.isSSL ? 'https://' : 'http://') + connectionLink, this.login, this.password, (success: boolean, key: string) => {
  //     //     this.connectGraph((this.isSSL ? 'wss://' : 'ws://') + connectionLink + '/ws?gc-auth-key=' + key.split('#')[0], (g) => {
  //     //       if (g) {
  //     //         this.save();
  //     //       }
  //     //       cb(g);
  //     //     });
  //     //   });
  //     // } else {
        this.connectGraph(null, (g) => {
          if (g) {
            this.save();
          }
          cb(g);
        });
  //     // }
    }
  }

  public disconnect(callback: (done: boolean) => void) {
    window.sessionStorage.removeItem(Connection.STORAGE_KEY);
    this.graph.disconnect(callback);
  }


  private static STORAGE_KEY: string = 'com.datathings.inspector.connection';
  private static STORAGE_SECRET: string = 'AB6F5A2BE4A64DCF40D4F9FD361AF8EF6527F7D779F9797037CDFA4C9BFDA797';

  public save() {
    let bin: { [key: string]: any } = {};
    for (let att in this) {
      if (att !== 'graph' && this.hasOwnProperty(att)) {
        bin[att] = this[att];
      }
    }
    console.log('save connection:', bin);
    let jsonized = JSON.stringify(bin);
    let encripted: any = CryptoJS.AES.encrypt(jsonized, Connection.STORAGE_SECRET);
    window.sessionStorage.setItem(Connection.STORAGE_KEY, encripted.toString());
  }

  public static loadOngoingConnection(): Connection {
    // let storedConnection = window.sessionStorage.getItem(Connection.STORAGE_KEY);
    // if (storedConnection) {
    //   let decrypted: any = CryptoJS.AES.decrypt(storedConnection, Connection.STORAGE_SECRET);
    //   let content = JSON.parse(decrypted.toString(CryptoJS.enc.Utf8));
    //   let connect = new Connection();
    //   for (let att in content) {
    //     connect[att] = content[att];
    //   }
    //   return connect;
    // }
    // return null;

      let connect = new Connection();
      // connect.connectGraph(null, () => {});
      return connect;


  }

  private connectGraph(url: string, cb: (graph: Graph) => any) {
    // let _graph: Graph = GraphBuilder.newBuilder().withStorage(new WSClient(url)).build();
    // let _graph: Graph = GraphBuilder.newBuilder().withStorage(new greycatincub.rocksdb.RocksDBStorage("./data")).build();

    
    var db = level('./mydb')
    db.put('name', 'Level', function (err) {
      if (err) return console.log('Ooops!', err) // some kind of I/O error
    
      // 3) Fetch by key
      db.get('name', function (err, value) {
        if (err) return console.log('Ooops!', err) // likely the key was not found
    
        // Ta da!
        console.log('name=' + value)
      })
    })

    let _graph: Graph = GraphBuilder.newBuilder().build();
    _graph.connect((result) => {
      if (result === true) {
        this.graph = _graph;
        cb(_graph);
      } else {
        console.error('Could not connect to ' + url);
        cb(null);
      }
    });
  }

  // private static authenticate(url: string, login: string, pass: string, cb: (authenticated: boolean, authenticationKey?: string) => any) {
  //   let fd = new FormData();
  //   fd.append('login', login);
  //   fd.append('pass', pass);
  //
  //   let xhr: XMLHttpRequest = new XMLHttpRequest();
  //   xhr.open('POST', url + '/auth');
  //   xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
  //   xhr.onerror = (event: ErrorEvent) => {
  //     console.error(event);
  //     if (cb) {
  //       cb(false, null);
  //     }
  //   };
  //   xhr.onreadystatechange = (event: ProgressEvent) => {
  //     let xhr = event.target as XMLHttpRequest;
  //     if (xhr.readyState === 4) {
  //       if (xhr.status == 200) {
  //         if (cb) {
  //           cb(true, xhr.responseText);
  //         }
  //       } else {
  //         if (cb) {
  //           cb(false, null);
  //         }
  //       }
  //     }
  //   };
  //   xhr.send(fd);
  // }

}

export default Connection;
