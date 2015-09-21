$(document).ready(function () {

    refresh();

    function refresh() {
        $('#films').empty();
        $.ajax({
            url: "/films",
            success: function (data) {
                if (data.length > 0) {
                    var markup = "<tr id='focus_${id}'><td>${name}</td><td>${genre}</td><td>${year}</td><td>${mark}</td><td>${dateSeen}</td><td>${review}</td><td><input type='checkbox' id='${id}'></td></tr>";
                    $.template("filmsTemplate", markup);
                    $.tmpl("filmsTemplate", data).appendTo('#films');
                } else {
                    $('#films').append("<tr><td colspan='7'><li class='list-group-item'>No films added!</li></td></tr>");
                }
            },
            timeout: 10000
        });
    }

    $('#btn-refresh').click(function () {
        refresh();
    });

    $('#btn-delete').click(function () {
        var ids = $('input:checkbox:checked').map(function () {
            return $(this).attr('id');
        }).get();

        if (ids.length > 0) {
            $.ajax({
                type: "POST",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(ids),
                url: "/film/delete",
                success: function () {
                    refresh();
                },
                timeout: 10000
            });
        }
    });

    $('#films').on('mouseover', 'tr', function () {
        var id = $(this).attr('id');
        $('#' + id).addClass('focus-color');
    });

    $('#films').on('mouseout', 'tr', function () {
        var id = $(this).attr('id');
        $('#' + id).removeClass('focus-color');
    });

    $('#edit-modal').on('show.bs.modal', function () {
        var checkedCount = $('input:checkbox:checked').length;
        if (checkedCount > 1) {
           getWarnPage();
        } else if (checkedCount == 1) {
            getEditPage();
        } else {
            getAddPage();
        }
    });

    function getWarnPage() {
        $('#modal-text').text("Please, select just one row to be edited!");
        $('#btn-save').addClass('non-visible');
        $('#btn-add').addClass('non-visible');
    }

    function getEditPage() {
        var id = $('input:checkbox:checked').attr('id');
        $.ajax({
            url: "/film/" + id,
            success: function (data) {
                $('film-name').val(data.name);
                $('film-genre').val(data.genre);
                $('film-mark').val(data.mark);
                $('film-year').val(data.year);
                $('film-date-seen').val(data.dateSeen);
                $('film-review').val(data.review);
                enableEvents();
                $('#btn-add').addClass('non-visible');
            },
            timeout: 10000
        });
    }

    function getAddPage() {
        enableEvents();
        $('#btn-save').addClass('non-visible');
    }

    function cleanModalAndRefresh() {
        $('#modal-text').empty();
        $('#error').empty();
        $('#btn-save').removeClass('non-visible');
        $('#btn-add').removeClass('non-visible');
        $('#error').addClass('non-visible');
        $('#edit-modal').modal('hide');
        refresh();
    }

    function createFilmAndReturn(id) {
        var name = $.trim($('#film-name').val());
        var genre = $.trim($('#film-genre').val());
        var mark = $('#film-mark').val();
        var year = $('#film-year').val();
        var date_seen = $('#film-date-seen').val();
        var review = $.trim($('#film-review').val());
        return {
            "id": id,
            "name": name,
            "genre": genre,
            "mark": mark,
            "year": year,
            "dateSeen": date_seen,
            "review": review
        };
    }

    $('#btn-save').click(function () {
        var film = createFilmAndReturn($('input:checkbox:checked').attr('id'));
        var name = $('#film-name').val();
        var genre = $('#film-genre').val();
        var mark = $('#film-mark').val();
        var year = $('#film-year').val();
        if (validate(name, genre, mark, year, -1)) {
            $.ajax({
                type: "POST",
                url: "/film/update",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(film),
                success: function () {
                    cleanModalAndRefresh();
                },
                error: function (data) {
                    $('#error').removeClass('non-visible');
                    $('#error').text(data.responseText);
                },
                timeout: 10000
            });
        }
    });

    $('#btn-add').click(function () {
        var film = createFilmAndReturn(-1);
        var name = $('#film-name').val();
        var genre = $('#film-genre').val();
        var mark = $('#film-mark').val();
        var year = $('#film-year').val();
        var date_seen = $('#film-date-seen').val();
        if (validate(name, genre, mark, year, date_seen)) {
            $.ajax({
                type: "POST",
                url: "/film",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(film),
                success: function () {
                    cleanModalAndRefresh();
                },
                error: function (data) {
                    $('#error').removeClass('non-visible');
                    $('#error').text(data.responseText);
                },
                timeout: 10000
            });
        }
    });

    function enableEvents() {
        $('#film-date-seen').datepicker({dateFormat: "yy-mm-dd", maxDate: "+1w"});

        $('#film-name').keypress(function (e) {
            if ($('#film-name').val().length > 44) {
                e.preventDefault();
            }
        });

        $('#film-genre').keypress(function (e) {
            if ($('#film-genre').val().length > 44) {
                e.preventDefault();
            }
        });

        $('#film-review').keypress(function (e) {
            if ($('#film-review').val().length > 399) {
                e.preventDefault();
            }
        });
    }

    $('#btn-cancel, #btn-close').click(function () {
        cleanModalAndRefresh();
    });

    function validate(name, genre, mark, year, date_seen) {
        var error = "";
        if (!$.trim(name)) {
            $('#film-name').addClass('border-error');
            error += "Name can't be empty! ";
        } else {
            $('#film-name').addClass('border-success');
        }
        if (!$.trim(genre)) {
            $('#film-genre').addClass('border-error');
            error += "Genre can't be empty! ";
        } else {
            $('#film-genre').addClass('border-success');
        }
        if (Math.floor(mark) != mark || !$.isNumeric(mark) || parseInt(mark) < 1 || parseInt(mark, 10) > 10) {
            $('#film-mark').addClass('border-error');
            error += "Mark must be a number from 1 to 10! ";
        } else {
            $('#film-mark').addClass('border-success');
        }
        if (Math.floor(year) != year || !$.isNumeric(year) || parseInt(year) < 1901 || parseInt(year, 10) > 2015) {
            $('#film-year').addClass('border-error');
            error += "Year must be a number from 1901 to 2015! ";
        } else {
            $('#film-year').addClass('border-success');
        }
        if (date_seen != -1) {
            if (!$.trim(date_seen)) {
                $('#film-date-seen').addClass('border-error');
                error += "Date can't be empty! ";
            } else {
                $('#film-date-seen').addClass('border-success');
            }
        }
        if ($.trim(error)) {
            $('#error').removeClass('non-visible');
            $('#error').text(error);
            return false;
        }
        return true;
    }

});

