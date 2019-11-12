var sendmail = {

    send: function(successCallback, errorCallback, subject, body, sender, password, recipients, attachment, mailhost, port){
        cordova.exec(successCallback,
            errorCallback,
            "SendMail",
            "send",
            [{
                 "subject":subject,
                 "body":body,
                 "sender":sender,
                 "password":password,
                 "recipients":recipients,
                 "attachment": attachment,
                 "mailhost": mailhost,
                 "port": port,
            }]
        );
    }
}

module.exports = sendmail;
