/**
 * 
 * HTTP cloud function provides greeting feature. Responds to any HTTP request
 * that can provide "name" field in HTTP body or as a request parameter.
 *
 * @param {!Object} req Cloud Function request context.
 * @param {!Object} res Cloud Function response context.
 */
var mysql = require('mysql');
var con = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'admin',
    database:'booking'
});

con.connect(function(err) {
    if (err) {console.log('NOT Con!');}
    console.log('Connected!');
});

/* jshint node: true */

/*require('@google/cloud-debug');
exports.helloHttp = function helloHttp(req, res) {

  // Example input: ?name=Pera or {"name": "Pera"}
  var name = req.query.name || req.body.name;
  if (name === undefined) {
    // This is an error case, as "name" is required.
    console.warn('Bad request: No name provided.');
    res.status(400).send('Greskka!');
  } else {
    // Everything is okay.
    console.log('Sending a greeting to: ' + name);
    res.status(200).send('Hello11 ' + name + '!');
  }
};
con.query('UPDATE `rating` SET `star`=? WHERE  `id`=1;',[1],
    function (err, results, fields) {
        if (err) {
            console.log(err);
            //throw err;
            //res.status(400).send('Greskka!');
        }
        console.log(results);
    });
*/
//var cors = require('cors')();

var express = require('express');
var bodyParser = require('body-parser');
require('@google/cloud-debug');
var app = express();

app.use(bodyParser());



app.post('/',exports.newRating = function newRating(req, res){


    //var smestajID = req.query.lodgingID;



        var name = req.query.name || req.body.name;
        name ='samoProba';
        if (name === undefined) {
            // This is an error case, as "name" is required.
            console.warn('Bad request: No name provided.');
            res.status(400)
        } else {
            // Everything is okay.
            console.log('Sending a greeting to: ' + name);
            con.query('UPDATE `rating` SET `star`=? WHERE  `id`=1;',[5],
                function (err, results, fields) {
                    if (err) {
                        console.log(err);
                        //throw err;
                        //res.status(400).send('Greskka!');
                    }
                    console.log(results);
                });
            res.status(200)
        }

}
)