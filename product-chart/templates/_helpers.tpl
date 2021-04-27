{{/*
Expand the name of the chart.
*/}}
{{- define "product-chart.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "product-chart.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "product-chart.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "product-chart.labels" -}}
helm.sh/chart: {{ include "product-chart.chart" . }}
{{ include "product-chart.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "product-chart.selectorLabels" -}}
app.kubernetes.io/name: {{ include "product-chart.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
project: product
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "product-chart.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "product-chart.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Names of other components
*/}}

{{- define "product-chart.urlConfig.defaultName" -}}
{{- printf "url-config-%s" .Release.Name -}}
{{- end -}}

{{/*
Names of product-service layer components
*/}}
{{- define "product-chart.product-service.defaultName" -}}
{{- printf "product-service-%s" .Release.Name -}}
{{- end -}}

{{- define "product-chart.product-service.deployment.name" -}}
{{- default (include "product-chart.product-service.defaultName" .) .Values.product_service.deployment.name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "product-chart.product-service.container.name" -}}
{{- default (include "product-chart.product-service.defaultName" .) .Values.product_service.container.name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "product-chart.product-service.service.name" -}}
{{- default (include "product-chart.product-service.defaultName" .) .Values.product_service.service.name | trunc 63 | trimSuffix "-" -}}
{{- end -}}


{{/*
Names of product-db layer components
*/}}
{{- define "product-chart.product-db.defaultName" -}}
{{- printf "product-db-%s" .Release.Name -}}
{{- end -}}

{{- define "product-chart.product-db.deployment.name" -}}
{{- default (include "product-chart.product-db.defaultName" .) .Values.product_db.deployment.name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "product-chart.product-db.container.name" -}}
{{- default (include "product-chart.product-db.defaultName" .) .Values.product_db.container.name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "product-chart.product-db.service.name" -}}
{{- default (include "product-chart.product-db.defaultName" .) .Values.product_db.service.name | trunc 63 | trimSuffix "-" -}}
{{- end -}}