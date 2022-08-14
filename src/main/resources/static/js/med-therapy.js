$(document).ready(function () {
    let user = $("#principalName").val();

    let get_btns = $(".get-drug-btn");
    for (let i = 0; i < get_btns.length; i++) {
        if ($(get_btns[i]).attr("data-stock") === 'false') {
            $(get_btns[i]).css("pointer-events", "none")
                .removeClass('btn-primary')
                .addClass('btn-danger');
        }
    }

    updateNotifications(user);

    $(".delete-btn").click(function (e) {
        let id = $(e.currentTarget).attr("data-drug-id");

        $.ajax({
            type: "GET",
            url: "/api/drug/" + id + "/delete",
            success: function (response) {
                console.log(response);
            },
            error: function (req, status, error) {
                console.log(req);
            }
        });
    });

    let medTherapyStatus = $("#medicalTherapyStatus").val();
    if (medTherapyStatus === "COMPLETED") {
        updateNotifications();
        $(".get-drug-btn").css("pointer-events", "none")
            .removeClass('btn-primary')
            .addClass('btn-secondary');
    }
});