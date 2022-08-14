$(document).ready(function () {
    let user = $("#principalName").val();

    if (user !== undefined) {
        updateNotifications(user);
    }

    $("#notifications-menu-btn").click(function () {
        $(".notification-list").toggle();

        $.ajax({
            type: "GET",
            url: "/api/notification/" + user + "/seen",
            success: function (response) {
                updateNotifications(user);
            },
            error: function (req, status, error) {
                console.log(req);
            }
        });
    });
});

function updateNotifications(user) {
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