<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:p="http://univ.fr/sepa">	
	<xsl:output method="html" version="5.0" encoding="UTF-8" indent="yes"/>
	<!-- Template principal pour la construction de la page -->
	<xsl:template match="/p:CstmrDrctDbtInitn">
		<!-- Informations de la page -->
		<xsl:element name="head">
			<xsl:element name="title">Transactions</xsl:element>
			<xsl:element name="link">
				<xsl:attribute name="href">tp3.sepa.css</xsl:attribute>
				<xsl:attribute name="rel">stylesheet</xsl:attribute>
			</xsl:element>
		</xsl:element>

		<!-- Corps de la page -->
		<xsl:element name="html">
			<xsl:element name="body">
				<!-- Haut de page -->
				<xsl:element name="header">
					<xsl:element name="h1">Transactions SEPA</xsl:element>
					<xsl:element name="p">Date d'émission : 03 février 2017</xsl:element>
				</xsl:element>
				
				<!-- Article contenant les transactions -->
				<xsl:for-each select="DrctDbtTxInf">
					<xsl:element name="h2"> 
						Transaction : <xsl:value-of select="PmtInf/PmtInfId"></xsl:value-of>
					</xsl:element>
					<xsl:element name="article">
						<xsl:call-template name="paymentInfos"></xsl:call-template>
						<xsl:call-template name="debitorInfos"></xsl:call-template>
						<xsl:call-template name="comments"></xsl:call-template>
					</xsl:element>
				</xsl:for-each>
				
				<!-- Bas de page -->
				<xsl:element name="footer">
					<xsl:element name="p">Document émis par Franck Caron</xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<!-- Template des sections des informations de paiement -->
	<xsl:template name="paymentInfos">
		<xsl:element name="section">
			<xsl:element name="table">
				<xsl:element name="tr">
					<xsl:element name="td">Montant</xsl:element>
					<xsl:element name="td">
						<xsl:value-of select="InstdAmt"></xsl:value-of>
						<xsl:value-of select="InstdAmt/@Ccy"></xsl:value-of>
					</xsl:element>
				</xsl:element>
				<xsl:element name="tr">
					<xsl:element name="td">Date</xsl:element>
					<xsl:element name="td">
						<!-- Formatage de la Date -->
						<xsl:variable name="d" select="DrctDbtTx/DtOfSgntr"></xsl:variable>
						<xsl:variable name="m" select="substring($d, 6, 2)"></xsl:variable>
						<xsl:value-of select="substring($d, 9)"></xsl:value-of>
						<xsl:choose>
							<xsl:when test="$m = '01'"><xsl:text> Janvier </xsl:text></xsl:when>
							<xsl:when test="$m = '02'"><xsl:text> Février </xsl:text></xsl:when>
							<xsl:when test="$m = '03'"><xsl:text> Mars </xsl:text></xsl:when>
							<xsl:when test="$m = '04'"><xsl:text> Avril </xsl:text></xsl:when>
							<xsl:when test="$m = '05'"><xsl:text> Mai </xsl:text></xsl:when>
							<xsl:when test="$m = '06'"><xsl:text> Juin </xsl:text></xsl:when>
							<xsl:when test="$m = '07'"><xsl:text> Juillet </xsl:text></xsl:when>
							<xsl:when test="$m = '08'"><xsl:text> Août </xsl:text></xsl:when>
							<xsl:when test="$m = '09'"><xsl:text> Septembre </xsl:text></xsl:when>
							<xsl:when test="$m = '10'"><xsl:text> Octobre </xsl:text></xsl:when>
							<xsl:when test="$m = '11'"><xsl:text> Novembre </xsl:text></xsl:when>
							<xsl:when test="$m = '12'"><xsl:text> Décembre </xsl:text></xsl:when>
						</xsl:choose>
						<xsl:value-of select="substring($d, 1, 4)"></xsl:value-of>
					</xsl:element>
				</xsl:element>
				<xsl:element name="tr">
					<xsl:element name="td">Transac</xsl:element>
					<xsl:element name="td"><xsl:value-of select="DrctDbtTx/MndtId"></xsl:value-of></xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<!-- Template des sections des informations de débit -->
	<xsl:template name="debitorInfos">
		<xsl:element name="section">
			<xsl:element name="h3">Débiteur</xsl:element>
			<xsl:element name="table">
				<xsl:element name="tr">
					<xsl:element name="td">Nom</xsl:element>
					<xsl:element name="td"><xsl:value-of select="Dbtr/Nm"></xsl:value-of></xsl:element>
				</xsl:element>
				
				<xsl:element name="tr">
					<xsl:choose>
						<!-- Ajout du BIC -->
						<xsl:when test="DbtrAgt/BIC">
							<xsl:element name="td">BIC</xsl:element>
							<xsl:element name="td">
								<xsl:value-of select="substring(DbtrAgt/BIC, 1, 4)"></xsl:value-of>
						        <xsl:text> </xsl:text>
						        <xsl:value-of select="substring(DbtrAgt/BIC, 5, 2)"></xsl:value-of>
						        <xsl:text> </xsl:text>
						        <xsl:value-of select="substring(DbtrAgt/BIC, 7, 2)"></xsl:value-of>
						        <xsl:text> </xsl:text>
						        <xsl:value-of select="substring(DbtrAgt/BIC, 9)"></xsl:value-of>
							</xsl:element>
						</xsl:when>
						<!-- Ajout du nom d'agent -->
						<xsl:when test="DbtrAgt/Othr/Id">
							<xsl:element name="td">Agent</xsl:element>
							<xsl:element name="td">
								<xsl:value-of select="DbtrAgt/Othr"></xsl:value-of>
							</xsl:element>
						</xsl:when>
					</xsl:choose>
				</xsl:element>
				
				<xsl:element name="tr">
					<xsl:choose>
						<!-- Ajout de l'IBAN -->
						<xsl:when test="DbtrAcct/IBAN">
							<xsl:element name="td">IBAN</xsl:element>
							<xsl:element name="td">
								<xsl:value-of select="DbtrAcct/IBAN"></xsl:value-of>
							</xsl:element>
						</xsl:when>
						
						<!-- Ajout du numéro de compte -->
						<xsl:when test="DbtrAcct/PrvtId">
							<xsl:element name="td">Compte</xsl:element>
							<xsl:element name="td">
								<xsl:value-of select="DbtrAcct/PrvtId/Othr/Id"></xsl:value-of>
							</xsl:element>
						</xsl:when>
					</xsl:choose>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<!-- Template des sections des commentaires -->
	<xsl:template name="comments">
		<xsl:element name="section">
			<xsl:element name="h3">Comment</xsl:element>
			<xsl:element name="p">
				<xsl:value-of select="RmtInf"></xsl:value-of>
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>