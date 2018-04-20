labels <- colnames(car.test.frame)
labels <- labels[which(labels != "Type")]

for (label in labels) {
	attrs <- labels[which(labels != label)]
	txt <- paste("Type~", paste(collapse="+", attrs))
	ad.car <- rpart(as.formula(txt), car.test.frame, control = ad.car.cnt)
	score <- min(ad.car$cptable[, 4] + ad.car$cptable[, 5])

	print(paste(label, score))
}