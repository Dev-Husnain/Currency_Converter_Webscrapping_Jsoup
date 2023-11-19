package com.currencyconverter.utils

import android.view.View
import android.widget.Toast
import com.currencyconverter.base.MyAppClass
import com.currencyconverter.domain.models.CurrencyModel

object Constants {
    //    https://www.forbes.com/advisor/money-transfer/currency-converter/usd-pkr/?amount=1
    const val BASE_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/"


    fun Double.roundOff(): Double {
        return String.format("%.1f", this).toDouble()
    }
    fun String.mToast(){
        try {
            MyAppClass.appContext?.let {
                Toast.makeText(it, this, Toast.LENGTH_SHORT).show()
            }
        }catch (_:Exception){}
    }
    fun moreVisibilities(vararg view: View,whichVGI: Int){
        view.forEach {
            it.changeVisibility(whichVGI)
        }
    }

    fun countriesList(): ArrayList<CurrencyModel> {
        return ArrayList<CurrencyModel>().apply {
            add(CurrencyModel("AED", "UAE Dirham"))
            add(CurrencyModel("AFN", "Afghan Afghani"))
            add(CurrencyModel("ALL", "Albanian Lek"))
            add(CurrencyModel("AMD", "Armenian Dram"))
            add(CurrencyModel("ANG", "Netherlands Antillean Guilder"))
            add(CurrencyModel("AOA", "Angolan Kwanza"))
            add(CurrencyModel("ARS", "Argentine Peso"))
            add(CurrencyModel("AUD", "Australian Dollar"))
            add(CurrencyModel("AWG", "Aruban Florin"))
            add(CurrencyModel("AZN", "Azerbaijani Manat"))
            add(CurrencyModel("BAM", "Bosnia-Herzegovina (CM)"))
            add(CurrencyModel("BBD", "Barbadian Dollar"))
            add(CurrencyModel("BDT", "Bangladeshi Taka"))
            add(CurrencyModel("BGN", "Bulgarian Lev"))
            add(CurrencyModel("BHD", "Bahraini Dinar"))
            add(CurrencyModel("BIF", "Burundian Franc"))
            add(CurrencyModel("BMD", "Bermudan Dollar"))
            add(CurrencyModel("BND", "Brunei Dollar"))
            add(CurrencyModel("BOB", "Bolivian Boliviano"))
            add(CurrencyModel("BRL", "Brazilian Real"))
            add(CurrencyModel("BSD", "Bahamian Dollar"))
            add(CurrencyModel("BTC", "Bitcoin"))
            add(CurrencyModel("BTN", "Bhutanese Ngultrum"))
            add(CurrencyModel("BTS", "BitShares"))
            add(CurrencyModel("BWP", "Botswanan Pula"))
            add(CurrencyModel("BYN", "Belarusian Ruble"))
            add(CurrencyModel("BZD", "Belize Dollar"))
            add(CurrencyModel("CAD", "Canadian Dollar"))
            add(CurrencyModel("CDF", "Congolese Franc"))
            add(CurrencyModel("CHF", "Swiss Franc"))
            add(CurrencyModel("CLF", "Chilean Unit of Account (UF)"))
            add(CurrencyModel("CLP", "Chilean Peso"))
            add(CurrencyModel("CNH", "Chinese Yuan (Offshore)"))
            add(CurrencyModel("CNY", "Chinese Yuan"))
            add(CurrencyModel("COP", "Colombian Peso"))
            add(CurrencyModel("CRC", "Costa Rican Colón"))
            add(CurrencyModel("CUC", "Cuban Convertible Peso"))
            add(CurrencyModel("CUP", "Cuban Peso"))
            add(CurrencyModel("CVE", "Cape Verdean Escudo"))
            add(CurrencyModel("CZK", "Czech Koruna"))
            add(CurrencyModel("DASH", "Dash"))
            add(CurrencyModel("DJF", "Djiboutian Franc"))
            add(CurrencyModel("DKK", "Danish Krone"))
            add(CurrencyModel("DOGE", "DogeCoin"))
            add(CurrencyModel("DOP", "Dominican Peso"))
            add(CurrencyModel("DZD", "Algerian Dinar"))
            add(CurrencyModel("EAC", "EarthCoin"))
            add(CurrencyModel("EGP", "Egyptian Pound"))
            add(CurrencyModel("EMC", "Emercoin"))
            add(CurrencyModel("ERN", "Eritrean Nakfa"))
            add(CurrencyModel("ETB", "Ethiopian Birr"))
            add(CurrencyModel("ETH", "Ethereum"))
            add(CurrencyModel("EUR", "Euro"))
            add(CurrencyModel("FCT", "Factom"))
            add(CurrencyModel("FJD", "Fijian Dollar"))
            add(CurrencyModel("FKP", "Falkland Islands Pound"))
            add(CurrencyModel("FTC", "Feathercoin"))
            add(CurrencyModel("GBP", "British Pound Sterling"))
            add(CurrencyModel("GEL", "Georgian Lari"))
            add(CurrencyModel("GGP", "Guernsey Pound"))
            add(CurrencyModel("GHS", "Ghanaian Cedi"))
            add(CurrencyModel("GIP", "Gibraltar Pound"))
            add(CurrencyModel("GMD", "Gambian Dalasi"))
            add(CurrencyModel("GNF", "Guinean Franc"))
            add(CurrencyModel("GTQ", "Guatemalan Quetzal"))
            add(CurrencyModel("GYD", "Guyanaese Dollar"))
            add(CurrencyModel("HKD", "Hong Kong Dollar"))
            add(CurrencyModel("HNL", "Honduran Lempira"))
            add(CurrencyModel("HRK", "Croatian Kuna"))
            add(CurrencyModel("HTG", "Haitian Gourde"))
            add(CurrencyModel("HUF", "Hungarian Forint"))
            add(CurrencyModel("IDR", "Indonesian Rupiah"))
            add(CurrencyModel("ILS", "Israeli New Sheqel"))
            add(CurrencyModel("IMP", "Manx pound"))
            add(CurrencyModel("INR", "Indian Rupee"))
            add(CurrencyModel("IQD", "Iraqi Dinar"))
            add(CurrencyModel("IRR", "Iranian Rial"))
            add(CurrencyModel("ISK", "Icelandic Króna"))
            add(CurrencyModel("JEP", "Jersey Pound"))
            add(CurrencyModel("JMD", "Jamaican Dollar"))
            add(CurrencyModel("JOD", "Jordanian Dinar"))
            add(CurrencyModel("JPY", "Japanese Yen"))
            add(CurrencyModel("KES", "Kenyan Shilling"))
            add(CurrencyModel("KGS", "Kyrgystani Som"))
            add(CurrencyModel("KHR", "Cambodian Riel"))
            add(CurrencyModel("KMF", "Comorian Franc"))
            add(CurrencyModel("KPW", "North Korean Won"))
            add(CurrencyModel("KRW", "South Korean Won"))
            add(CurrencyModel("KWD", "Kuwaiti Dinar"))
            add(CurrencyModel("KYD", "Cayman Islands Dollar"))
            add(CurrencyModel("KZT", "Kazakhstani Tenge"))
            add(CurrencyModel("LAK", "Laotian Kip"))
            add(CurrencyModel("LBP", "Lebanese Pound"))
            add(CurrencyModel("LD", "Linden Dollar"))
            add(CurrencyModel("LKR", "Sri Lankan Rupee"))
            add(CurrencyModel("LRD", "Liberian Dollar"))
            add(CurrencyModel("LSL", "Lesotho Loti"))
            add(CurrencyModel("LTC", "LiteCoin"))
            add(CurrencyModel("LYD", "Libyan Dinar"))
            add(CurrencyModel("MAD", "Moroccan Dirham"))
            add(CurrencyModel("MDL", "Moldovan Leu"))
            add(CurrencyModel("MGA", "Malagasy Ariary"))
            add(CurrencyModel("MKD", "Macedonian Denar"))
            add(CurrencyModel("MMK", "Myanma Kyat"))
            add(CurrencyModel("MNT", "Mongolian Tugrik"))
            add(CurrencyModel("MOP", "Macanese Pataca"))
            add(CurrencyModel("MRU", "Mauritanian Ouguiya"))
            add(CurrencyModel("MUR", "Mauritian Rupee"))
            add(CurrencyModel("MVR", "Maldivian Rufiyaa"))
            add(CurrencyModel("MWK", "Malawian Kwacha"))
            add(CurrencyModel("MXN", "Mexican Peso"))
            add(CurrencyModel("MYR", "Malaysian Ringgit"))
            add(CurrencyModel("MZN", "Mozambican Metical"))
            add(CurrencyModel("NAD", "Namibian Dollar"))
            add(CurrencyModel("NGN", "Nigerian Naira"))
            add(CurrencyModel("NIO", "Nicaraguan Córdoba"))
            add(CurrencyModel("NMC", "Namecoin"))
            add(CurrencyModel("NOK", "Norwegian Krone"))
            add(CurrencyModel("NPR", "Nepalese Rupee"))
            add(CurrencyModel("NVC", "NovaCoin"))
            add(CurrencyModel("NXT", "Nxt"))
            add(CurrencyModel("NZD", "New Zealand Dollar"))
            add(CurrencyModel("OMR", "Omani Rial"))
            add(CurrencyModel("PAB", "Panamanian Balboa"))
            add(CurrencyModel("PEN", "Peruvian Nuevo Sol"))
            add(CurrencyModel("PGK", "Papua New Guinean Kina"))
            add(CurrencyModel("PHP", "Philippine Peso"))
            add(CurrencyModel("PKR", "Pakistani Rupee"))
            add(CurrencyModel("PLN", "Polish Zloty"))
            add(CurrencyModel("PPC", "Peercoin"))
            add(CurrencyModel("PYG", "Paraguayan Guarani"))
            add(CurrencyModel("QAR", "Qatari Rial"))
            add(CurrencyModel("RON", "Romanian Leu"))
            add(CurrencyModel("RSD", "Serbian Dinar"))
            add(CurrencyModel("RUB", "Russian Ruble"))
            add(CurrencyModel("RWF", "Rwandan Franc"))
            add(CurrencyModel("SAR", "Saudi Riyal"))
            add(CurrencyModel("SBD", "Solomon Islands Dollar"))
            add(CurrencyModel("SCR", "Seychellois Rupee"))
            add(CurrencyModel("SDG", "Sudanese Pound"))
            add(CurrencyModel("SEK", "Swedish Krona"))
            add(CurrencyModel("SGD", "Singapore Dollar"))
            add(CurrencyModel("SHP", "Saint Helena Pound"))
            add(CurrencyModel("SLL", "Sierra Leonean Leone"))
            add(CurrencyModel("SOS", "Somali Shilling"))
            add(CurrencyModel("SRD", "Surinamese Dollar"))
            add(CurrencyModel("SSP", "South Sudanese Pound"))
            add(CurrencyModel("STD", "São Tomé & Príncipe Dobra"))
            add(CurrencyModel("STN", "São Tomé & Príncipe Dobra"))
            add(CurrencyModel("STR", "Stellar"))
            add(CurrencyModel("SVC", "Salvadoran Colón"))
            add(CurrencyModel("SYP", "Syrian Pound"))
            add(CurrencyModel("SZL", "Swazi Lilangeni"))
            add(CurrencyModel("THB", "Thai Baht"))
            add(CurrencyModel("TJS", "Tajikistani Somoni"))
            add(CurrencyModel("TMT", "Turkmenistani Manat"))
            add(CurrencyModel("TND", "Tunisian Dinar"))
            add(CurrencyModel("TOP", "Tongan Pa'anga"))
            add(CurrencyModel("TRY", "Turkish Lira"))
            add(CurrencyModel("TTD", "Trinidad & Tobago Dollar"))
            add(CurrencyModel("TWD", "New Taiwan Dollar"))
            add(CurrencyModel("TZS", "Tanzanian Shilling"))
            add(CurrencyModel("UAH", "Ukrainian Hryvnia"))
            add(CurrencyModel("UGX", "Ugandan Shilling"))
            add(CurrencyModel("USD", "United States Dollar"))
            add(CurrencyModel("UYU", "Uruguayan Peso"))
            add(CurrencyModel("UZS", "Uzbekistan Som"))
            add(CurrencyModel("VEF", "Venezuelan Bolívar Fuerte"))
            add(CurrencyModel("VES", "Venezuelan Bolívar Soberano"))
            add(CurrencyModel("VND", "Vietnamese Dong"))
            add(CurrencyModel("VTC", "VertCoin"))
            add(CurrencyModel("VUV", "Vanuatu Vatu"))
            add(CurrencyModel("WST", "Samoan Tala"))
            add(CurrencyModel("XAF", "CFA Franc BEAC"))
            add(CurrencyModel("XAG", "Silver Ounce"))
            add(CurrencyModel("XAU", "Gold Ounce"))
            add(CurrencyModel("XCD", "East Caribbean Dollar"))
            add(CurrencyModel("XDR", "Special Drawing Rights"))
            add(CurrencyModel("XMR", "Monero"))
            add(CurrencyModel("XOF", "CFA Franc BCEAO"))
            add(CurrencyModel("XPD", "Palladium Ounce"))
            add(CurrencyModel("XPF", "CFP Franc"))
            add(CurrencyModel("XPM", "Primecoin"))
            add(CurrencyModel("XPT", "Platinum Ounce"))
            add(CurrencyModel("XRP", "Ripple"))
            add(CurrencyModel("YER", "Yemeni Rial"))
            add(CurrencyModel("ZAR", "South African Rand"))
            add(CurrencyModel("ZMW", "Zambian Kwacha"))
            add(CurrencyModel("ZWL", "Zimbabwean Dollar"))
        }
    }
}

fun View.changeVisibility(whichVGI: Int) {
    when (whichVGI) {
        0 -> this.visibility = View.VISIBLE
        1 -> this.visibility = View.GONE
        2 -> this.visibility = View.INVISIBLE
    }
}