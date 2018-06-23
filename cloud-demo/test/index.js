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
    password: 'stefan',
    database:'booking'
});

con.connect(function(err) {
    if (err) {console.log('NOT Con!');}
    console.log('Connected!');
});

var express = require('express');
var bodyParser = require('body-parser');
require('@google/cloud-debug');
var app = express();
app.use(bodyParser());
//app.post('/',exports.newRating = function newRating(req, res){

exports.newRating = function (req, res) {
        var star = req.query.star || req.body.star;
        var lodging = req.query.lodging || req.body.lodging;
        var user = req.query.user || req.body.user;
        var dateCreated = req.query.dateCreated || req.body.dateCreated;
        var accepted = req.query.accepted || req.body.accepted;
        var body = req.query.body || req.body.body;
        var avg =0.0;
        var sum= 0;
        if (star === undefined) {
            // This is an error case, as "name" is required.
            console.warn('Bad request: No name provided.');
            res.status(400).send('greska');
        } else {

            con.query('select * from rating where lodging_id='+lodging+ ' and user_id=' + user +';', function(err, results){
                if(err){
                    res.status(400).send("errr");
                }else {

                    if(results.length == 0) {
                        con.query('insert into rating (lodging_id, user_id, star, date_created) values (' + lodging + ', ' + user + ', ' + star + ', ' + ' \'2018-06-26\' '+')')
                    } else {
                        con.query('update rating set star=' + star + ' where lodging_id=' + lodging + ' and user_id=' + user + '');
                    }

                    con.query('select * from rating where lodging_id ='+ lodging+';',
                        function(err,results){
                            for (i = 0; i < results.length; i++) {
                                sum = sum + results[i].star;
                            }
                            avg = sum/ results.length;

                            con.query('update lodging set rating = '+avg+' where id = '+lodging+';')
                            console.warn('Bad request: No name provided.' + avg);

                           // res.status(200).send("avg = "+avg +"   i  sum =" + sum +" results ->" +results+" res[0] ->"+ results[0].star);
                    })
                   // res.status(200).send("Uspesno dodat ili izmenjen.");
                }
            })

        }

    con.query('insert into comment (accepted, body, lodging_id, user_id) values (' + accepted + ', \'' + body + ' \', ' + lodging + ', ' + user +')',
        function(err,result){
            if(err){
                res.status(400).send("errr");
            }else{
               res.status(200).send("comment cloud");
            }
        })


}


exports.searchRating = function (req, res) {
    var ratingValue = req.query.ratingValue || req.body.ratingValue;
    var ratingBig = ratingValue + 1;
    var ratingSmall = ratingValue - 1;
    var retString="";
    var retString1="";
    var lodgingId= [] ;

    lodgingId = req.lodgingId;
    res.status(200).send('aaaaaaaaaaaaaaaaaaaaaaa ' lodgingId[0].);
   /* for (i = 0; i < lodgingId.length; i++) {
        retString1 = retString1 = lodging.id;
    }
    res.status(200).send(retString1);
    if (ratingValue === undefined) {
        // This is an error case, as "name" is required.
        console.warn('Bad request: No name provided.');
        res.status(400).send('greska');
    } else {
        for (i = 0; i < lodgingId.length; i++) {
            con.query('select * from lodging where rating<' + ratingBig + ' and rating>' + ratingSmall + ' and id ='+lodgingId.id+';'
                , function (err, results) {

                    retString = retString + results[i].id + ",";


                //res.status(200).send(retString);
            })
        }
        res.status(200).send(retString);
    }*/
}