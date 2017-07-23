var express = require('express');
var router = express.Router();
var path = require('path');
var formidable = require('formidable'); //parse the incoming form data (the uploaded files)
var fs = require('fs'); //for renaming uploaded files
var sql = require('mssql');
var talename, taledescription, creator, email;

const config = {
    user: 'pesnaTales',
    password: 'Admin123',
    server: 'pesnatales.database.windows.net',
    database: 'pesnaTales',

    options: {
        encrypt: true // for Windows Azure
    }
};

/* GET submitTale page. */
router.get('/', function(req, res, next) {
    res.render('submitTale', {  output:req.params.talename,
                                output:req.params.taledescription,
                                output:req.params.creator,output:req.params.email });
    });



router.post('/uploadb',function(req,res){
    talename = req.body.talename;
    taledescription = req.body.taledescription;
    creator = req.body.creator;
    email = req.body.email;
    // connect to your database
    sql.connect(config, function (err) {
        if (err) console.log(err);
        // create Request object
        var request = new sql.Request();
        // query to the database and get the records
        request.query( "insert into Tales (tale_name,tale_description,creator,email) values ('"+talename+"','"+taledescription+"','" + creator +"','"+email+"')",
            function (err) {
                if (err){ console.log(err);}
                //TODO:create JSON file for the tale (get files by creation date)
                var obj = { tale_name: req.body.talename, tale_description:req.body.taledescription,
                    creator:req.body.creator,email:req.body.email
                 };
                var json = JSON.stringify(obj);
                fs.writeFile("./jsondata/" +req.body.talename+"_"+req.body.creator+'.json', json, 'utf8');


                //fs.appendFile('jsons.txt',req.body.talename+"_"+req.body.creator+"\n" , 'utf8');


                var data = fs.readFileSync('jsons.txt'); //read existing contents into data
                var fd = fs.openSync('jsons.txt', 'w+');
                var buffer = new Buffer(req.body.talename+"_"+req.body.creator+"\n");

                fs.writeSync(fd, buffer, 0, buffer.length, 0); //write new data
                fs.writeSync(fd, data, 0, data.length, buffer.length); //append old data
                fs.close(fd);

            })});
    //sql.close();
    res.render('submitComplete', {message1:"Your tale has been successfully submitted.",
        message2:"Your tale will appear on our site as soon as possible."});
});

/* UPLOAD .psn file */
router.post('/upload',function (req, res){

    console.log(talename);
    console.log(taledescription);
    console.log(creator);
    console.log(email);
      // create an incoming form object
    var form = new formidable.IncomingForm();

    // specify that we want to allow the user to upload multiple files in a single request
    //form.multiples = true;

    // store all uploads in the /uploads directory
    form.uploadDir = path.join(__dirname, '../uploads');

    // every time a file has been uploaded successfully,
    // rename it to it's orignal name
    form.on('file', function(field, file) {
        fs.rename(file.path, path.join(form.uploadDir, talename+"_"+creator));
    });

    // log any errors that occur
    form.on('error', function(err) {
        console.log('An error has occured: \n' + err);
    });

    // once all the files have been uploaded, send a response to the client
    form.on('end', function() {

        res.end('success');

    });

    // parse the incoming request containing the form data
    form.parse(req);

});

module.exports = router;
