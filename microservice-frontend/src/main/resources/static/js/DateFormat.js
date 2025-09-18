document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.birth-date').forEach(function(el) {
        let iso;
        if (el.tagName === 'INPUT') {
            iso = el.value || el.getAttribute('value');
        } else {
            iso = el.textContent.trim();
        }
        if (iso && iso.match(/^\d{4}-\d{2}-\d{2}/)) {
            const d = new Date(iso);
            const formatted = d.toLocaleDateString('fr-FR');
            if (el.tagName === 'INPUT') {
                el.value = formatted;
            } else {
                el.textContent = formatted;
            }
        }
    });
    document.querySelectorAll('.note-date').forEach(function(el) {
        const iso = el.textContent.trim();
        if (iso.match(/^\d{4}-\d{2}-\d{2}/)) {
            const d = new Date(iso);
            el.textContent = d.toLocaleDateString('fr-FR') + (d.getHours() !== 0 || d.getMinutes() !== 0 ? ' ' + d.toLocaleTimeString('fr-FR', {hour: '2-digit', minute: '2-digit'}) : '');
        }
    });
});