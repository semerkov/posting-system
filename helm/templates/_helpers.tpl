{{- define "postingSystem.labels" }}
  labels:
    version: {{ quote .Chart.Version }}
    date: {{ now | htmlDate }}
{{- end }}