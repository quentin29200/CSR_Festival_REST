/**
 * CSR_Festival_REST
 * Fichier implémenté dans le cadre du cours de CSR et du TP REST en Java.
 *
 * @authors : Nicolas GIGOU & Quentin LAGADEC
 */

$(document).ready(function() {

    /**
     * Création d'un nombre de festivaliers donné par l'utilisateur
     * via le formulaire de la page "Ajouter des festivaliers"
     */
    $("#create-user-form button").click( function() {
        var nb_users = new Object();
        nb_users["length"] = $("#count-add-festiv").val();

        console.log("Nb nouveaux festivaliers a ajouter : " + JSON.stringify(nb_users));

        $.ajax({
            type: "post",
            url: "/people",
            dataType: "json",
            contentType : "application/json",
            data: JSON.stringify(nb_users),
            success: function(data){
                console.log("SUCCESS. YOU ADD FESTIVALIERS. YOUR PRESENT : "  + data);
                window.location = "/";
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Ajax error with add users");
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    })

    /**
     * Ajout de N bus
     * via le formulaire de la page "Ajouter des bus"
     */
    $("#create-buses-form button").click( function() {
        var nb_buses = new Object();
        nb_buses["nbbus"] = $("#count-add-buses").val();

        console.log("Nb nouveaux bus a ajouter : " + JSON.stringify(nb_buses));

        $.ajax({
            type: "post",
            url: "/buses",
            dataType: "json",
            contentType : "application/json",
            data: JSON.stringify(nb_buses),
            success: function(data){
                console.log("SUCCESS. YOU ADD FESTIVALIERS. YOUR PRESENT : "  + data);
                /* window.location = "/"; */
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Ajax error with add users");
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    })

    /**
     * Liste les festivaliers
     */
    $('#btn_generate_list').click( function() {
        var users_table = $('#users-table tbody');

        $.ajax({
            type: "get",
            url: "/people/",
            dataType: "json",
            contentType : "application/json",
            success: function(data){
                console.log("LIST-USER LOG DATA : " + data);

                $.each(data, function (item) {
                    var id = data[item].id;
                    var etatF = data[item].etatF;
                    var url = data[item].url;

                    users_table.append(
                    '<tr>' +
                        '<th><a href="' + url + '">' + id + '</a></th>' +
                        '<td>' + etatF + '</td>' +
                    '</tr>'
                    );

                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Ajax error with list users");
                console.log(jqXHR);
                console.log(textStatus);
            }
        });
    })

});