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

        console.log("Nb nouveaux festivaliers demandés : " + nb_users);

        $.ajax({
            type: "post",
            url: "/people",
            dataType: "json",
            contentType : "application/json",
            data: JSON.stringify(nb_users),
            success: function(data){
                console.log(data);
                window.location = "/";
            }
        });
    })

    /**
     * Liste les festivaliers
     */
    $('#list-users').ready( function() {
        var users_table = $('#users-table tbody');

        $.ajax({
            type: "get",
            url: "/people/",
            dataType: "json",
            contentType : "application/json",
            success: function(data){
                console.log(data);

                $.each(data, function (item) {
                    var id = data[item].id;
                    var nomF = data[item].nomF;
                    var prenomF = data[item].prenomF;

                    users_table.append(
                    '<tr>' +
                        '<th>' + id + '</th>' +
                        '<td>' + nomF + '</td>' +
                        '<td>' + prenomF + '</td>' +
                    '</tr>'
                    );

                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Ajax error with list users");
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    })

});