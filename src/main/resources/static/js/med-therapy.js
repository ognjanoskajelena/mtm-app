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

    updateNotifications();

    $(".delete-btn").click(function (e) {
        let id = $(e.currentTarget).attr("data-drug-id");

        $.ajax({
            type: "GET",
            url: "/api/drug/" + id + "/delete",
            success: function (response) {
                updateNotifications()
            },
            error: function (req, status, error) {
                console.log(req);
            }
        });
    });

    function updateNotifications() {
        $.ajax({
            type: "GET",
            url: "/api/notification/" + user,
            success: function (response) {
                let notif_count = 0;
                Array.prototype.reverse.call(response);
                let list = $(".notification-list").empty();
                for (let i = 0; i < response.length; i++) {
                    let sentAt = response[i].sentAt.split("T");
                    let date = sentAt[0];
                    let timeArray = sentAt[1].split(".");
                    let time = timeArray[0];
                    let card = `<div class="card">
                                        <div class="card-body">
                                            <p class="card-text">${response[i].content}</p>
                                            <p class="card-text d-flex justify-content-between">
                                            <small class="text-muted"><i class="fa fa-calendar"></i> ${date}</small>
                                            <small class="text-muted"><i class="fa fa-clock-o"></i> ${time}</small>
                                            </p>
                                        </div>
                                    </div>`;
                    list.append(card);
                    // console.log(response[i].seen)
                    if (!response[i].seen) {
                        ++notif_count;
                    }
                }
                if (notif_count > 0) {
                    $("#notifications-count").text(notif_count);
                } else {
                    $("#notifications-count").hide();
                }
            },
            error: function (req, status, error) {
                console.log(req);
            }
        });
    }

    let medTherapyStatus = $("#medicalTherapyStatus").val();
    if (medTherapyStatus === "COMPLETED") {
        updateNotifications();
        $(".get-drug-btn").css("pointer-events", "none")
            .removeClass('btn-primary')
            .addClass('btn-secondary');
    }

    $("#notifications-menu-btn").click(function () {
        $(".notification-list").toggle();

        $.ajax({
            type: "GET",
            url: "/api/notification/" + user + "/seen",
            success: function (response) {
                updateNotifications()
            },
            error: function (req, status, error) {
                console.log(req);
            }
        });
    });
});