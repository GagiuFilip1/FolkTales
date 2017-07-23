var express = require('express');
var readline = require('readline');
var router = express.Router();
var fs = require('fs');

/* GET home page. */
router.get('/', function(req, res, next) {
    res.redirect('/tales/1');

});
router.get('/:id',function (req, res) {
    var id = req.params.id;
    id = parseInt(id);
    var respobj = {message:id};
    var tale_name=[]; var tale_description=[]; var creator = [];

    var cntr = 0, i=1;
    var rl = readline.createInterface({
        input: fs.createReadStream("jsons.txt")});

     rl.on('line', function(line) {
        cntr++;
        if (cntr <= (id*3) && cntr>(id-1)*3 ) {
            //console.log(line);
            var data = fs.readFileSync('./jsondata/'+ line +'.json');
            if(data!=null){
            var json = JSON.parse(data);
            tale_name[i]=json.tale_name;
            tale_description[i]=json.tale_description;
            creator[i] = json.creator;
            console.log(tale_name[i]+" "+tale_description[i]+" "+creator[i]);
            }
            i++;
            if(i==4){    res.render('./tales',{ tale_name1: tale_name[1], tale_description1:tale_description[1], creator1:creator[1],link1:tale_name[1]+"_"+creator[1]+"",
                tale_name2: tale_name[2], tale_description2:tale_description[2], creator2:creator[2],link2:tale_name[2]+"_"+creator[2]+".psn",
                tale_name3: tale_name[3], tale_description3:tale_description[3], creator3:creator[3],link3:tale_name[3]+"_"+creator[3]+".psn",
                id:id });
                rl.close();
                res.end();
            }
        }

        //else if(i!=3)res.send("no more tales");
    });

});
module.exports = router;
