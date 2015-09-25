$(document).ready(function () {

    refresh();

    function refresh() {
        $('#films').empty();
        var context_path = $('#contex-path').val();
        $.ajax({
            url: context_path + "/films",
            success: function (data) {
                if (data.length > 0) {
                    var markup = "<tr id='focus_${id}'><td>${name}</td><td>${genre}</td><td>${year}</td><td>${mark}</td><td>${dateSeen}</td><td>${review}</td><td><input type='checkbox' id='${id}'></td></tr>";
                    $.template("filmsTemplate", markup);
                    $.tmpl("filmsTemplate", data).appendTo('#films');
                } else {
                    $('#films').append("<li class='list-group-item'>No films added!</li>");
                }
            }
        });
    }

    $('#btn-refresh').click(function () {
        refresh();
    });

    $('#btn-delete').click(function () {
        var ids = $('input:checkbox:checked').map(function () {
            return $(this).attr('id');
        }).get();
        var context_path = $('#contex-path').val();
        if (ids.length > 0) {
            $.ajax({
                type: "POST",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(ids),
                url: context_path + "/film/delete",
                success: function () {
                    refresh();
                }
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
        $('#modal-text').addClass('non-visible');
        $('#btn-save').addClass('non-visible');
        $('#btn-add').addClass('non-visible');
        $('#error').removeClass('non-visible');
        $('#error').text("Please, select just one row to be edited!");
    }

    function getEditPage() {
        var context_path = $('#contex-path').val();
        var id = $('input:checkbox:checked').attr('id');
        $.ajax({
            url: context_path + "/film/" + id,
            success: function (data) {
                $('#film-name').val(data.name);
                $('#film-genre').val(data.genre);
                $('#film-mark').val(data.mark);
                $('#film-year').val(data.year);
                $('#film-date-seen').val(data.dateSeen);
                $('#film-review').val(data.review);
                $('#btn-add').addClass('non-visible');
            }
        });
    }

    function getAddPage() {
        $('#btn-save').addClass('non-visible');
    }

    function cleanModalAndRefresh() {
        cleanBorders();
        $('#modal-text').trigger("reset");
        $('#modal-text').removeClass('non-visible');
        $('#btn-save').removeClass('non-visible');
        $('#btn-add').removeClass('non-visible');
        $('#error').addClass('non-visible');
        $('#error').empty();
        $('#edit-modal').modal('hide');
        refresh();
    }

    function validateAndEdit(id, date_seen_validate, url) {
        cleanBorders();
        var name = $.trim($('#film-name').val());
        var genre = $.trim($('#film-genre').val());
        var mark = $('#film-mark').val();
        var year = $('#film-year').val();
        var date_seen = $('#film-date-seen').val();
        var review = $.trim($('#film-review').val());
        var context_path = $('#contex-path').val();
        if (validate(name, genre, mark, year, date_seen_validate, review)) {
            var film = {
                "id": id,
                "name": name,
                "genre": genre,
                "mark": mark,
                "year": year,
                "dateSeen": date_seen,
                "review": review
            };
            $.ajax({
                type: "POST",
                url: context_path + url,
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
                }
            });
        }
    }

    $('#btn-save').click(function () {
        var id = $('input:checkbox:checked').attr('id');
        var url = "/film/update";
        var date_seen_validate = -1;
        validateAndEdit(id, date_seen_validate, url)
    });

    $('#btn-add').click(function () {
        var id = -1;
        var url = "/film";
        var date_seen_validate = $('#film-date-seen').val();
        validateAndEdit(id, date_seen_validate, url)
    });

    $('#film-date-seen').datepicker({dateFormat: "yy-mm-dd", maxDate: "+1w"});

    $('#btn-cancel, #btn-close').click(function () {
        cleanModalAndRefresh();
    });

    function cleanBorders() {
        $('#modal-text input').each(function () {
            $('#' + $(this).attr('id')).removeClass('border-error border-success');
        });
        $('#film-review').removeClass('border-error border-success');
    }

    function validate(name, genre, mark, year, date_seen, review) {
        var error = "";
        error += Validation.validName(name);
        error += Validation.validGenre(genre);
        error += Validation.validMark(mark);
        error += Validation.validYear(year);
        error += Validation.validDateSeen(date_seen);
        error += Validation.validReview(review);
        if (error) {
            $('#error').removeClass('non-visible');
            $('#error').text(error);
            return false;
        }
        return true;
    }

});

